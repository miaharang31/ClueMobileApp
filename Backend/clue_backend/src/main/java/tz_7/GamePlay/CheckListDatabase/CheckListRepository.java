package tz_7.GamePlay.CheckListDatabase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList, Integer> {
    CheckList findByID(int ID);
}
