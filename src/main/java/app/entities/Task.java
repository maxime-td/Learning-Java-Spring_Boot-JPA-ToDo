package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="tasks")
@Getter @Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="category")
    private Category category;

    @Column(nullable = false)
    private String content;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Task(Category category, String content) {
        this.category = category;
        this.content = content;
        this.creationDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, category='%s', content='%s', creationDate='%s', endDate='%s']",
                id, category.getName(), content, creationDate, endDate);
    }
}