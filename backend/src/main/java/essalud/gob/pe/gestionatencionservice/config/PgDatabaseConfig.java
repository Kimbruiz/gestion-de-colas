package essalud.gob.pe.gestionatencionservice.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableJpaRepositories(basePackages = PgDatabaseConfig.BASE_PACKAGE + ".jpa.repository")
@MapperScan(basePackages = PgDatabaseConfig.BASE_PACKAGE + ".my_repository", annotationClass = Repository.class, sqlSessionFactoryRef = "sqlSessionFactory")
@ComponentScan({PgDatabaseConfig.BASE_PACKAGE + ".*"})
@EntityScan(PgDatabaseConfig.BASE_PACKAGE + ".jpa.entity")
public class PgDatabaseConfig {

    public static final String BASE_PACKAGE = "essalud.gob.pe.gestionatencionservice";
    private static final String _MAPPER_FOLDER = "sqlmapPg";

    @Primary
    @Bean(name = "dataSource", destroyMethod = "")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, dataSource);
        return sessionFactoryBean.getObject();
    }

    private void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(BASE_PACKAGE);
        sessionFactoryBean.setConfigLocation(new ClassPathResource(String.format("%s/mybatis-config.xml",_MAPPER_FOLDER)));
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(String.format("classpath:%s/mapper/*.xml",_MAPPER_FOLDER)));
    }
}