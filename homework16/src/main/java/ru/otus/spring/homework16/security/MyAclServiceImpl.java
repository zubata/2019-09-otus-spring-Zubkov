package ru.otus.spring.homework16.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework16.domain.Book;

@Service
@RequiredArgsConstructor
public class MyAclServiceImpl implements MyAclService {

    private final MutableAclService mutableAclService;
    private final Sid admin = new PrincipalSid("admin");
    private final Sid user1 = new GrantedAuthoritySid("ROLE_USERADULT");
    private final Sid user2 = new GrantedAuthoritySid("ROLE_USERNOADULT");

    public void createACL(Book book, boolean isAdultBook) {
        ObjectIdentity businessEntity = new ObjectIdentityImpl(book);
        MutableAcl acl = mutableAclService.createAcl(businessEntity);
        acl.setOwner(admin);
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, admin, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, admin, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, admin, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, user1, true);
        if (!isAdultBook) {
            acl.insertAce(acl.getEntries().size(), BasePermission.READ, user2, true);
        }
        mutableAclService.updateAcl(acl);
    }

    public void removeACL(long id) {
        ObjectIdentity businessEntity = new ObjectIdentityImpl(Book.class, id);
        mutableAclService.deleteAcl(businessEntity, false);
    }

    public boolean isAdmin() { return true; }
}
