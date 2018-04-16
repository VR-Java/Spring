/*
1 Ticket = 1 Price
==========================================================================
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "price")
@NoArgsConstructor
@Getter
@Setter
public class PriceEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /*
    strategies:
    
    AUTO (default): 
    Tells Doctrine to pick the strategy that is preferred by the used database platform. 
    The preferred strategies are IDENTITY for MySQL, SQLite and MsSQL and SEQUENCE for Oracle and PostgreSQL. 
    This strategy provides full portability.

    SEQUENCE: 
    Tells Doctrine to use a database sequence for ID generation. This strategy does currently not provide full portability. 
    Sequences are supported by Oracle and PostgreSql.

    IDENTITY: 
    Tells Doctrine to use special identity columns in the database that generate a value on insertion of a row. 
    This strategy does currently not provide full portability and is supported by the following platforms:

                MySQL/SQLite => AUTO_INCREMENT
                MSSQL => IDENTITY
                PostgreSQL => SERIAL

    TABLE: 
    Tells Doctrine to use a separate table for ID generation. This strategy provides full portability. 
    This strategy is not yet implemented!

    NONE: 
    Tells Doctrine that the identifiers are assigned, and thus generated, by your code. 
    The assignment must take place before a new entity is passed to EntityManager#persist. 
    NONE is the same as leaving off the @GeneratedValue entirely.

    UUID: 
    SINCE VERSION 2.3
    Tells Doctrine to use the built-in Universally Unique Identifier generator. 
    This strategy provides full portability.
    
    */

    @NotEmpty
    @OneToOne(mappedBy = "price", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private TicketEntity ticket;

    /*
    if we need delete connected item together with this one:
    cascade = CascadeType.ALL                             
    
    if we need to perform all operations, except delete:
    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    
    */
