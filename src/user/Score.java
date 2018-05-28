package user;

public class Score {
    private float score;
    private float totalScore;

    public Score(float score, float totalScore) {
        this.score = score;
        this.totalScore = totalScore;
    }

    public float getScore() {
        return score;
    }

    public float getTotalScore() {
        return totalScore;
    }
}
