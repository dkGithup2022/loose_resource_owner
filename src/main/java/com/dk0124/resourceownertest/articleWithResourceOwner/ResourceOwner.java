package com.dk0124.resourceownertest.articleWithResourceOwner;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(indexes = {@Index(name = "index_on_ipvr", columnList = "ipv4"), @Index(name = "index_on_userId", columnList = "userId")})
@NoArgsConstructor
@ToString
public class ResourceOwner {
    @Id
    @Column(name = "resource_owner_id")
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private OwnerType ownerType;
    private Long userId;
    private String ipv4;

    protected ResourceOwner(OwnerType ownerType, Long userId, String ipv4) {
        this.ownerType = ownerType;
        this.userId = userId;
        this.ipv4 = ipv4;
    }

    public static ResourceOwner ofIpv4(String ipv4) {
        return new ResourceOwner(OwnerType.IPv4, null, ipv4);
    }

    public static ResourceOwner ofUserId(Long userId) {
        return new ResourceOwner(OwnerType.USER, userId, null);
    }

}
