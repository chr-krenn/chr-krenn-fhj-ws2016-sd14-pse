package at.fhj.swd14.pse.community;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserCommunityPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long user_id;
    private Long community_id;

    public UserCommunityPK(Long userId, Long communityId) {
        user_id = userId;
        community_id = communityId;

    }

    public UserCommunityPK() {
        // default public constructor
    }

    @Override
    public int hashCode() {
        return (int) (community_id + user_id);
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