## ch0301_jpa-demo

### JPA: Java Persistence API
JPA 为对象关系映射提供了一种基于 POJO 的持久化模型
* 简化数据持久化代码的开发工作
* 为 Java 社区屏蔽不同持久化 API 的差异
* 2006 年，JPA 1.0 作为 JSR 220 的⼀部分正式发布

使用 `joda-money` 表示金额，并使用 `@Type` 持久化到数据库


### 常⽤ JPA 注解
#### 实体
* @Entity、@MappedSuperclass
* @Table(name)
#### 主键
* @Id
* @GeneratedValue(strategy, generator)
* @SequenceGenerator(name, sequenceName)
#### 映射
* @Column(name, nullable, length, insertable, updatable)
* @JoinTable(name)、@JoinColumn(name)
#### 关系
* @OneToOne、@OneToMany、@ManyToOne、@ManyToMany
* @OrderBy
