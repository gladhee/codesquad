import java.util.List;

public class Hanoi {

    private static final int MAX_WIDTH = 10;

    private final Desk[] desks;
    private final int target;
    private int current;

    public Hanoi(int target) {
        this.desks = new Desk[3];
        for (int i = 0; i < 3; i++) {
            this.desks[i] = new Desk(i + 1);
        }
        desks[0].push(Parts.COMPUTE);
        desks[0].push(Parts.DISPLAY);
        desks[0].push(Parts.DRV);
        this.target = Math.min(target, 8);
        this.current = 1;
    }

    public void solve() {
        if (this.target == 1) {
            return;
        }
        move(3, desks[0], desks[1], desks[2]);
    }

    private boolean move(int n, Desk from, Desk aux, Desk to) {
        if (n == 1) {
            return movePart(from, to);
        }
        else {
            if (move(n - 1, from, to, aux)) {
                return true;
            }
            if (movePart(from, to)) {
                return true;
            }
            return move(n - 1, aux, from, to);
        }
    }

    private boolean movePart(Desk from, Desk to) {
        Parts part = from.pop();
        to.push(part);
        current++;

        return current == target;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Step ").append(this.current).append("\n");

        List<String> desk1 = this.desks[0].toList();
        List<String> desk2 = this.desks[1].toList();
        List<String> desk3 = this.desks[2].toList();

        for (int i = 0; i < MAX_WIDTH; ++i) {
            sb.append(String.join(" ", List.of(
                    desk1.get(i),
                    desk2.get(i),
                    desk3.get(i)
            )));
            sb.append("\n");
        }

        return sb.toString();
    }

}
