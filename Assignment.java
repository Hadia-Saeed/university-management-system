import java.io.Serializable;

public class Assignment implements Serializable{
    private String assignmentID, title;
    private int totalMarks;

    public Assignment(String assignmentID, String title, int totalMarks) {
        setAssignmentID(assignmentID);
        setTitle(title);
        setTotalMarks(totalMarks);
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotalMarks(int totalMarks) {
        if(totalMarks < 0){
            System.err.println("Total marks cannot be negative.");
        }
        this.totalMarks = totalMarks;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public String toString() {
        return "AssignmentID: " + assignmentID + "\nTitle: " + title + "\nTotalMarks: " + totalMarks;
    }

}//end of Assignment class
