package demo.dengchao.resilience4j.feign.broken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ip {

    public static final Ip LOCALHOST = new Ip("localhost");

    private String origin;
}
