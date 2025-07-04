package PollingSystem;

import PollingSystem.DTOs.Poll;
import PollingSystem.DTOs.Vote;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PollingService {

    Map<String, Poll> polls = new ConcurrentHashMap<>();
    Map<String,Map<String, Integer>> pollWiseResult = new ConcurrentHashMap<>();

    Map<String, Set<String>> pollUserVotes; // Stores pollId -> Set of userIds who voted

    public String createPoll(String question, Map<Integer, String> answers) {

        Poll poll = new Poll();
        poll.setPollId(UUID.randomUUID().toString()); // Generate unique poll ID
        poll.setQuestion(question);
        poll.setAnswerOptions(answers);
        poll.setCreatedAt(new Date());
        polls.put(poll.getPollId(),poll);// Set the current timestamp
        return poll.toString();


    }

    public String updatePoll(String question,Map<Integer, String> answers, String pollId){

        Poll poll = polls.get(pollId);
        if(poll == null)
            return "poll not found";
        poll.setQuestion(question);
        poll.setAnswerOptions(answers);
        return  poll.toString();
    }

    public String deletePoll(String pollId){
        polls.remove(pollId);
        pollWiseResult.remove(pollId);
        pollUserVotes.remove(pollId);
        return "Poll removed" ;
    }

    public String voteInPoll(String pollId, String userId, String optionId){
        if (!polls.containsKey(pollId)) return "Poll does not exist";

        pollUserVotes.putIfAbsent(pollId, new HashSet<>());
        if (pollUserVotes.get(pollId).contains(userId)) {
            return "User has already voted in this poll";
        }
        Vote vote = new Vote();
        vote.setVoteId(UUID.randomUUID().toString());
        vote.setPollId(pollId);
        vote.setDate(new Date());
        vote.setOptionId(optionId);
        vote.setUserId(userId);
        pollWiseResult.putIfAbsent(pollId, new HashMap<>());
        Map<String, Integer> optionVotes = pollWiseResult.get(pollId);
        optionVotes.putIfAbsent(optionId,0);
        optionVotes.put(optionId, optionVotes.get(optionId)+1);
        return "Vote cast successfully";

    }

    public  Map<String, Integer> viewPollResults(int pollId){
        return pollWiseResult.getOrDefault(pollId, new HashMap<>());

    }
}
