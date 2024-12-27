package com.example.demo.config;

import com.datastax.oss.driver.api.core.CqlSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.ReactiveSession;
import org.springframework.data.cassandra.ReactiveSessionFactory;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.cql.ReactiveCqlOperations;
import org.springframework.data.cassandra.core.cql.session.DefaultSessionFactory;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.net.InetSocketAddress;
import java.time.Duration;

@Configuration
@EnableReactiveCassandraRepositories(basePackages = "com.example.demo.repository")
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {
    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.local-datacenter}")
    private String localDatacenter;

    @Value("${spring.cassandra.port}")
    private int port;

    @Value("${spring.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.cassandra.username}")
    private String username;

    @Value("${spring.cassandra.password}")
    private String password;

    @Value("${spring.cassandra.schema-action}")
    private SchemaAction schemaAction;

    @Value("${spring.cassandra.request.timeout}")
    private Duration requestTimeout;

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return schemaAction;
    }

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Bean
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setUsername(username);
        cqlSessionFactoryBean.setPassword(password);
        cqlSessionFactoryBean.setKeyspaceName(keySpace);
        cqlSessionFactoryBean.setLocalDatacenter(localDatacenter);
        cqlSessionFactoryBean.setPort(port);
        cqlSessionFactoryBean.setContactPoints(contactPoints);
        return cqlSessionFactoryBean;
    }
}
