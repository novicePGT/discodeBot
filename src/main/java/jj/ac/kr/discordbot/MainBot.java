package jj.ac.kr.discordbot;

import io.github.cdimascio.dotenv.Dotenv;
import jj.ac.kr.discordbot.listener.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class MainBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * 환경 변수를 로드하고, 샤드 매니저를 빌드한다.
     * @throws LoginException -> 로그인 토큰이 유효하지 않으면 LoginException이 발생한다.
     */

    public MainBot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN"); // 토큰 값은 공개되지 않아야함 -> env 파일의 TOKEN을 환경변수로 저장해 가져옴

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder
                .createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE); // 아이콘의 상태, 온라인이면 초록색 알갱이를 나타냄.
        builder.setActivity(Activity.watching("자바와 함께")); // 활동하는 것을 나타낸다.
        shardManager = builder.build(); // 토큰이 올바르지 않거나 로그인 예외를 발생시키는지 확인하는 절차를 거침.

        // 이벤트리스너 등록 과정
        shardManager.addEventListener(new EventListener());
    }

    /**
     * 샤드 매니저를 리턴한다.
     * @return
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     * 봇의 환경 변수를 리턴한다.
     * @return
     */
    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) {
        try {
            MainBot bot = new MainBot();
        }catch (LoginException loginException) {
            System.out.println("[MAIN] : LoginException !! ");
            System.out.println("[HOW TO SOLVED] : Check your token validity");
        }
    }
}
