package user;

public class StateAgeAccept {
    private States state;
    private int startRangeYearOld;
    private int endRangeYearOld;

    public StateAgeAccept(States state, int startRangeYearOld, int endRangeYearOld) {
        this.state = state;
        this.startRangeYearOld = startRangeYearOld;
        this.endRangeYearOld = endRangeYearOld;
    }

    public States getState() {
        return state;
    }

    public int getStartRangeYearOld() {
        return startRangeYearOld;
    }

    public int getEndRangeYearOld() {
        return endRangeYearOld;
    }
}
