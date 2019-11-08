package provider.spring.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LookupHuman {

    public void show() {
        log.info("{} show", this.getClass().getName());
    }
}

@Slf4j
class LookupTeacher extends LookupHuman {
    @Override
    public void show() {
        log.info("{} show", this.getClass().getName());
    }
}


