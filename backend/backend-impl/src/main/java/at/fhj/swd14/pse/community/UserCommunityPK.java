package at.fhj.swd14.pse.community;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserCommunityPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long communityId;

    public UserCommunityPK(Long userId, Long communityId) {
        this.userId = userId;
        this.communityId = communityId;
    }

    public UserCommunityPK() {
        // default public constructor
    }

    @Override
    public int hashCode() {
        return (int) (communityId + userId);
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