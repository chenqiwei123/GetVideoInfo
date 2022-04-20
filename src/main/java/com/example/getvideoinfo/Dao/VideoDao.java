package com.example.getvideoinfo.Dao;

import com.example.getvideoinfo.Entity.SaveVideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VideoDao extends JpaRepository<SaveVideoInfo, Long> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `videoInfo`(`id`, `href`, `img`, `language`, `title`, `content`,`download`) VALUES (?1,?2,?3,?4,?5,?6,?7)")
    int updateIndexCalculated(Long id, String href, String img, String language, String title, String content,String download);
}
