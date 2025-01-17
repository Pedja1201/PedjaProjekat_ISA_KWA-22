package rs.ac.singidunum.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Nastavnik extends Korisnik{
    @Column(nullable = true, unique = true)
    private String email;
    @Column(nullable = false)
    private String ime;
    @Column(nullable = true)
    private String biografija;
    @Column(nullable = false, unique = true)
    private String jmbg;

    @ManyToOne
    private Adresa adresa;
    @ManyToOne
    private Zvanje zvanje;

    //nastavnik je Dekan za fukalutet
    @OneToMany(mappedBy = "nastavnik")
    private Set<Fakultet> fakulteti = new HashSet<Fakultet>();

    @OneToMany(mappedBy = "nastavnik")
    private Set<StudijskiProgram> studijskiProgrami = new HashSet<StudijskiProgram>();

    @OneToMany(mappedBy = "nastavnik")
    private Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji = new HashSet<NastavnikNaRealizaciji>();

    @OneToMany(mappedBy = "nastavnik")
    private Set<Univerzitet> univerziteti = new HashSet<Univerzitet>();

    public Nastavnik() {super();
    }

    public Nastavnik(Long id, String korisnickoIme, String lozinka, String email, String ime, String biografija, String jmbg, Adresa adresa, Zvanje zvanje) {
        super(id, korisnickoIme, lozinka);
        this.email = email;
        this.ime = ime;
        this.biografija = biografija;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.zvanje = zvanje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Zvanje getZvanje() {
        return zvanje;
    }

    public void setZvanje(Zvanje zvanje) {
        this.zvanje = zvanje;
    }

    public Set<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(Set<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public Set<StudijskiProgram> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }

    public Set<NastavnikNaRealizaciji> getNastavniciNaRealizaciji() {
        return nastavniciNaRealizaciji;
    }

    public void setNastavniciNaRealizaciji(Set<NastavnikNaRealizaciji> nastavniciNaRealizaciji) {
        this.nastavniciNaRealizaciji = nastavniciNaRealizaciji;
    }

    public Set<Univerzitet> getUniverziteti() {
        return univerziteti;
    }

    public void setUniverziteti(Set<Univerzitet> univerziteti) {
        this.univerziteti = univerziteti;
    }
}
