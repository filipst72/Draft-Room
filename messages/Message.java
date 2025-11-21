package raf.draft.dsw.model.messages;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Message {
    private String poruka;

    public Message(String poruka) {
        this.poruka = poruka;
    }
    public Message(){};

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}
