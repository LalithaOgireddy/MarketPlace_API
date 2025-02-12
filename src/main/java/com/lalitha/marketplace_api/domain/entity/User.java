package com.lalitha.marketplace_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "ads")

@Entity
public class User {

    @Id
    @NotNull
    @Column(unique = true, updatable = false)
    private String email;

    @NotNull
    @Column(nullable = false, length = 50)
    private String password;
    private boolean expired;

    @OneToMany(mappedBy = "seller")
    private Set<Advertisement> ads;

    public User(@NotNull String email,@NotNull String password) {
        this.email = email;
        this.password = password;
        this.expired = false;
        this.ads = new HashSet<>();
    }

    public void addAd(Advertisement advertisement) {  // should we check for duplicates?
        if (advertisement == null) throw new IllegalArgumentException("Advertisement cannot be null");
        if(ads == null) ads = new HashSet<>();
        ads.add(advertisement);
        if(advertisement.getSeller() == null) advertisement.setSeller(this);
    }

    public void removeAd(Advertisement advertisement) {
        if (advertisement == null) throw new IllegalArgumentException("Advertisement cannot be null");
        if(ads != null && ads.contains(advertisement)) {
            ads.remove(advertisement);
            advertisement.setSeller(null);
        }
        else throw new IllegalArgumentException("Advertisement does not exist");
    }
}
