package com.epam.star.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image extends AbstractEntity {

    @Column(name = "filename")
    private String filename;

    @Lob
    @Column(name = "content")
    private byte[] content;

    public Image() {
    }

    public Image(int id, String filename, byte[] content) {
        super(id);
        this.filename = filename;
        this.content = content;
    }

    public Image(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }
    public byte[] getContent() {
        return content;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
}
