package MMT.DataSource;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PreValidation {
    public Boolean prevalidate(Map<String,String> filterAttributes, String dataSource);
}
