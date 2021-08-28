package me.myte.gpc.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Request() {
        date = new Date();
    }

    public Request(String requestText) {
        this();
        this.requestText = requestText;
    }

    private Date date;

    private String requestText;

}
