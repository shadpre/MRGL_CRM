package BE;

import java.time.LocalDateTime;

public class Document {
    private int Id;
    private byte[] SketchIMG;
    private String SketchDescription;
    private byte[] ImgBefore;
    private String DescriptionBefore;
    private byte[] ImgAfter;
    private LocalDateTime CreatedDate;
    private int UserID;

    public Document(int id, byte[] sketchIMG, String sketchDescription, byte[] imgBefore, String descriptionBefore, byte[] imgAfter, LocalDateTime createdDate, int userID) {
        Id = id;
        SketchIMG = sketchIMG;
        SketchDescription = sketchDescription;
        ImgBefore = imgBefore;
        DescriptionBefore = descriptionBefore;
        ImgAfter = imgAfter;
        CreatedDate = createdDate;
        UserID = userID;
    }
    public int getId() {
        return Id;
    }
    public byte[] getSketchIMG() {
        return SketchIMG;
    }
    public String getSketchDescription() {
        return SketchDescription;
    }
    public byte[] getImgBefore() {
        return ImgBefore;
    }
    public String getDescriptionBefore() {
        return DescriptionBefore;
    }
    public byte[] getImgAfter() {
        return ImgAfter;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }
    public int getUserID() {
        return UserID;
    }
}
