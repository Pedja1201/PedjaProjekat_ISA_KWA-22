package rs.ac.singidunum.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipNastave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "tipNastave")
    private Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji = new HashSet<NastavnikNaRealizaciji>();
    @OneToMany(mappedBy = "tipNastave")
    private Set<TerminNastave> terminiNastave = new HashSet<TerminNastave>();

    public TipNastave() {super();
    }

    public TipNastave(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<NastavnikNaRealizaciji> getNastavniciNaRealizaciji() {
        return nastavniciNaRealizaciji;
    }

    public void setNastavniciNaRealizaciji(Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji) {
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public Set<TerminNastave> getTerminiNastave() {
        return terminiNastave;
    }

    public void setTerminiNastave(Set<TerminNastave> terminiNastave) {
        this.terminiNastave = terminiNastave;
    }
}
