package practice.trello.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
}
