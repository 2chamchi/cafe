package com.errand.temp.repository.jdbc;

import com.errand.temp.domain.Cafe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JdbcCafeRepository implements CafeRepository {

     private final JdbcTemplate jdbcTemplate;
     private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCafeRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }


    @Override
    public Cafe save(Cafe cafe) {
        String sql = "insert into cafe(cafe_name) values (:cafeName)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(cafe);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedJdbcTemplate.update(sql, param, keyHolder);

        cafe.setCafeId(keyHolder.getKey().longValue());
        return cafe;
    }

    @Override
    public void update(Long cafeId, CafeUpdateDto updateDto) {
        String sql = "update cafe set cafe_name = :cafeName where cafe_id = :cafeId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("cafeName", updateDto.getCafeName())
                .addValue("cafeId", cafeId);
        namedJdbcTemplate.update(sql, param);
    }

    @Override
    public Optional<Cafe> findById(Long cafeId) {
        String sql = "select cafe_id, cafe_name from cafe where cafe_id = ?";
        Optional<Cafe> optional = Optional.empty();
        try {
            Cafe cafe = jdbcTemplate.queryForObject(sql, cafeBeanRowMapper(), cafeId);
            optional = Optional.of(cafe);
        } catch (EmptyResultDataAccessException ex) {
            log.info("?????? ????????? ????????????.");
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.error("????????? 2??? ?????? ?????????.");
        }
        return optional;
    }

    private RowMapper<Cafe> cafeRowMapper(){
        return ((rs, rowNum) -> {
            Cafe cafe = new Cafe();
            cafe.setCafeId(rs.getLong("cafe_id"));
            cafe.setCafeName(rs.getString("cafe_name"));
            return cafe;
        });
    }

    /**
     * DB ?????? ????????? Java ?????? ?????? ???????????? ?????? ??????
     * DB ??????(alias) ??? ???????????? ?????? ??????.
     */
    private RowMapper<Cafe> cafeBeanRowMapper(){
        /**
         * DB (_) underscore <-> Java camelCase ?????? ?????? ??????
         */
        return BeanPropertyRowMapper.newInstance(Cafe.class);
    }

    @Override
    public List<Cafe> findAll(CafeSearchCond cond) {
        List<Cafe> result;
        SqlParameterSource param = new BeanPropertySqlParameterSource(cond);
        String sql = "select cafe_id, cafe_name from cafe";
        if (cond.getCafeName() != null){
            sql += " where lower(cafe_name) like concat('%', lower(:cafeName), '%')";
        }
        log.info("sql={}", sql);
        result = namedJdbcTemplate.query(sql, param, cafeBeanRowMapper());

        return result;
    }
}
