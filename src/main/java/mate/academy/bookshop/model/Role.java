package mate.academy.bookshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE roles SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "role_name", nullable = false, unique = true)
    private RoleName roleName;
    @Column(nullable = false)
    private boolean isDeleted = false;

    @Override
    public String getAuthority() {
        return "ROLE_" + roleName.name();
    }

    public enum RoleName {
        USER,
        ADMIN
    }
}
