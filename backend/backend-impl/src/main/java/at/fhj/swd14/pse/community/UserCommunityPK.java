package at.fhj.swd14.pse.community;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserCommunityPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long user_Id;
    private Long community_Id;

    public UserCommunityPK(Long userId, Long communityId) {
        this.user_Id = userId;
        this.community_Id = communityId;
    }

    public UserCommunityPK() {
        // default public constructor
    }

    @Override
    public int hashCode() {
        return (int) (community_Id + user_Id);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        return getClass() == obj.getClass();

    }
}