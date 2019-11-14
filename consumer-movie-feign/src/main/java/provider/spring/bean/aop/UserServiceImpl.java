package provider.spring.bean.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JunWu
 */
@Slf4j
public class UserServiceImpl implements IUserService {
    @Override
    public void perform() {
      log.info("eating ....");
    }
}
