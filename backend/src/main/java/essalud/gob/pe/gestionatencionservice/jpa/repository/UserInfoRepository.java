package essalud.gob.pe.gestionatencionservice.jpa.repository;

import essalud.gob.pe.gestionatencionservice.jpa.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findFirstByGuiid(String guiid);

}
