package com.example.university.Repository;

import com.example.university.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group,Long> {

    @Query(
            "select  g from groups g inner join mark m on g.id=?1 order by m.mark desc "
    )
    Group findGroupByGroupId(Long id);
}
