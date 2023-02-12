package com.errand.temp.repository.jdbc;

import com.errand.temp.domain.Cafe;
import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;
import com.errand.temp.repository.memory.MemoryCafeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Spring @Transactional 은 테스트 환경에서는 특별하게 동작한다. (강제 rollback)
@Transactional
@SpringBootTest
class CafeRepositoryTest {

    @Autowired
    @Qualifier("jdbcCafeRepository")
    CafeRepository cafeRepository;

    // SpringBoot 에서는 자동으로 Bean 등록을 해준다.
    /*
    @Autowired
    PlatformTransactionManager transactionManager;
    TransactionStatus status;

    @BeforeEach
    void beforeEach(){
        // 트랜잭션 시작
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }
    */

    @AfterEach
    void afterEach(){
        if (cafeRepository instanceof MemoryCafeRepository){
            ((MemoryCafeRepository) cafeRepository).clearCafe();
        }
        //transactionManager.rollback(status);
    }

    @Test
    void save() {
        Cafe saveCafe = new Cafe(0L, "Sample Cafe");
        Cafe resultCafe = cafeRepository.save(saveCafe);
        Cafe findCafe = cafeRepository.findById(resultCafe.getCafeId()).get();
        assertThat(findCafe).isEqualTo(resultCafe);
    }

    // Spring @Commit 은 강제로 commit 수행
    //@Commit
    //@Transactional
    @Test
    void update() {
        String updateCafeName = "StartBucks";
        Cafe saveCafe = new Cafe(1L, "Sample Cafe");
        CafeUpdateDto updateDto = new CafeUpdateDto();
        updateDto.setCafeName(updateCafeName);
        cafeRepository.update(1L, updateDto);
        Cafe findCafe = cafeRepository.findById(1L).get();
        assertThat(updateCafeName).isEqualTo(findCafe.getCafeName());
    }

    @Test
    void findById() {
        Cafe resultCafe = cafeRepository.save(new Cafe(0L, "Sample Cafe"));
        Cafe findCafe = cafeRepository.findById(resultCafe.getCafeId()).get();
        assertThat(findCafe).isEqualTo(resultCafe);
    }

    @Test
    void findAll() {
        // TestDataInit + 추가 데이터
        Cafe cafe1 = new Cafe(1L, "Sample Cafe");
        Cafe cafe2 = new Cafe(2L, "StarBucks");
        cafeRepository.save(cafe1);
        cafeRepository.save(cafe2);
        List<Cafe> cafeList = cafeRepository.findAll(new CafeSearchCond());
        assertThat(6).isEqualTo(cafeList.size());
    }
}