package ladder.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

  private final BufferedReader reader;

  public InputView() {
    this.reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public List<String> getPlayerName() throws IOException {
    System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
    return Arrays.stream(reader.readLine().split(","))
        .map(String::strip)
        .collect(Collectors.toList());
  }

  public int ladderHeight() throws IOException {
    System.out.println("최대 사다리 높이는 몇 개인가요?");
    return Integer.parseInt(reader.readLine());
  }

  public void tearDown() throws IOException {
    this.reader.close();
  }
}
