package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) // 여기엔 team을 적으면 안됨. 왜냐면 team에 들어가면 거기에 또 member가 있음. 무한루프
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userName;
    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id") // 외래키명
    private Team team;

    public Member(String userName) {
        this.userName = userName;
    }

    public Member(String username, int age, Team team) {
        this.userName = username;
        this.age = age;
        if(team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMember().add(this);
    }

}