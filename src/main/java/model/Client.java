package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Client {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Long id;
    private String createdTime;
    private String deletedTime;

    public Client() {
    }

    public Client(String createdTime, String deletedTime) {
        this.createdTime = LocalDateTime.now().format(FORMATTER);
        this.deletedTime = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(String deletedTime) {
        this.deletedTime = deletedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client user = (Client) o;
        return Objects.equals(id, user.id)
                && Objects.equals(createdTime, user.createdTime)
                && Objects.equals(deletedTime, user.deletedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdTime, deletedTime);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", createdTime=" + createdTime
                + ", deletedTime=" + deletedTime
                + '}';
    }
}
