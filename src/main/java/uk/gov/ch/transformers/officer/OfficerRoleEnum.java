package uk.gov.ch.transformers.officer;

public enum OfficerRoleEnum {
    
    CIC("CIC", "cic-manager"),
    DIR("DIR", "director"),
    DIRCORP("DIRCORP", "corporate-director"),
    EEIGMAN("EEIGMAN", "manager-of-an-eeig"),
    FACTOR("FACTOR", "judicial-factor"),
    LLPDESMEM("LLPDESMEM", "llp-designated-member"),
    LLPGENPART("LLPGENPART", "general-partner-in-a-limited-partnership"),
    LLPLIMPART("LLPLIMPART", "limited-partner-in-a-limited-partnership"),
    LLPMEM("LLPMEM", "llp-member"),
    MEMADMORG("MEMADMORG", "member-of-an-administrative-orga"),
    MEMMANORG("MEMMANORG", "member-of-a-management-organ"),
    MEMSUPORG("MEMSUPORG", "member-of-a-supervisory-organ"),
    NOMDIR("NOMDIR", "nominee-director"),
    NOMSEC("NOMSEC", "nominee-secretary"),
    PERSAUTHA("PERSAUTHA", "person-authorised-to-accept"),
    PERSAUTHRA("PERSAUTHRA", "person-authorised-to-represent-and-accept"),
    PERSAUTHR("PERSAUTHR", "person-authorised-to-represent"),
    RECMAN("RECMAN", "receiver-and-manager"),
    SEC("SEC", "secretary"),
    SECCORP("SECCORP", "corporate-secretary");
//    DIRNAT("DIRNAT", ""),
//    MEMNAT("MEMNAT", ""),
//    MEMCORP("MEMCORP" ,""), 
//    MEMCORPDES("MEMCORPDES", ""),
//    MEMNATDES("MEMNATDES", ""),
//    SECNAT("SECNAT", ""),
    
    
    OfficerRoleEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    private String id;
    private String description;
    
    public static OfficerRoleEnum fromString(String id) {
        for(OfficerRoleEnum ore : OfficerRoleEnum.values()) {
            if(ore.id.equalsIgnoreCase(id)) {
                return ore;
            }
        }
        throw new IllegalArgumentException("No enum with id " + id + " exists");
    }
    
    public String getDescription() {
        return description;
    }
}
