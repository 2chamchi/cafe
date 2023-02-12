package com.errand.temp.repository.querydsl;

import com.errand.temp.domain.CafeOrder;
import com.errand.temp.domain.QCafeOrder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class QuerydslSpringDataJpaOrderRepository implements OrderRepository {

    private final EntityManager entityManger;
    private JPAQueryFactory queryFactory;

    public QuerydslSpringDataJpaOrderRepository(EntityManager entityManager) {
        this.entityManger = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public CafeOrder save(CafeOrder cafeOrder) {
        /* jpa */
        entityManger.persist(cafeOrder);
        return cafeOrder;
    }

    @Override
    public void update(Long orderId, OrderUpdateDto updateDto) {
        /* jpa */
        CafeOrder cafeOrder = entityManger.find(CafeOrder.class, orderId);
        cafeOrder.setOrderName(updateDto.getOrderName());
    }

    @Override
    public Optional<CafeOrder> findById(Long orderId) {
        /* jpa */
        CafeOrder cafeOrder = entityManger.find(CafeOrder.class, orderId);
        return Optional.ofNullable(cafeOrder);
    }

    @Override
    public List<CafeOrder> findAll(OrderSearchCond searchCond) {
        String orderName = searchCond.getOrderName();
        LocalDateTime orderDate = searchCond.getOrderDate();

        List<CafeOrder> result = queryFactory.select(QCafeOrder.cafeOrder)
                .from(QCafeOrder.cafeOrder)
                .where(likeOrderName(orderName), greaterThanOrderDate(orderDate))
                .fetch();

        return result;
    }

    public BooleanExpression greaterThanOrderDate(LocalDateTime orderDate){
        if(orderDate != null){
            return QCafeOrder.cafeOrder.orderDate.after(orderDate);
        }
        return null;
    }

    public BooleanExpression likeOrderName(String orderName){
        if(StringUtils.hasText(orderName)){
            return QCafeOrder.cafeOrder.orderName.like("%" + orderName + "%");
        }
        return null;
    }
}
