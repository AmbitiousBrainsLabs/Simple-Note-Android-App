package ke.co.ablabs.simplenote;

public class MyNotesDatabaseModel {
    public String notes;

    public MyNotesDatabaseModel() {
    }

    public MyNotesDatabaseModel(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MyNotesDatabaseModel{" + "notes='" + notes + '\'' + '}';
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}