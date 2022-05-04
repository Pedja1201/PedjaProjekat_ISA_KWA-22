import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MeniComponent } from './meni/meni.component';
import { WelcomeComponent } from './page/welcome/welcome.component';
import { NotFoundComponent } from './page/not-found/not-found.component';
import { PomeranjeKursoraDirective } from './directive/pomeranje-kursora.directive';
import { PorukaOGresciDirective } from './directive/poruka-ogresci.directive';
import { ProveraNepostojecihVrednostiDirective } from './directive/provera-nepostojecih-vrednosti.directive';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { LoginComponent } from './login/login.component';
import { AdreseComponent } from './page/adrese/adrese.component';
import { DrzaveComponent } from './page/drzave/drzave.component';
import { FakultetiComponent } from './page/fakulteti/fakulteti.component';
import { GodineStudijaComponent } from './page/godine-studija/godine-studija.component';
import { IshodiComponent } from './page/ishodi/ishodi.component';
import { MestaComponent } from './page/mesta/mesta.component';
import { NastavniciComponent } from './page/nastavnici/nastavnici.component';
import { NastavniciNaRealizacijiComponent } from './page/nastavnici-na-realizaciji/nastavnici-na-realizaciji.component';
import { NaucneOblastiComponent } from './page/naucne-oblasti/naucne-oblasti.component';
import { PohadjanjaPredmetaComponent } from './page/pohadjanja-predmeta/pohadjanja-predmeta.component';
import { PredmetiComponent } from './page/predmeti/predmeti.component';
import { RealizacijePredmetaComponent } from './page/realizacije-predmeta/realizacije-predmeta.component';
import { StudentiComponent } from './page/studenti/studenti.component';
import { StudentiNaGodiniComponent } from './page/studenti-na-godini/studenti-na-godini.component';
import { StudijskiProgramiComponent } from './page/studijski-programi/studijski-programi.component';
import { TipoviNastaveComponent } from './page/tipovi-nastave/tipovi-nastave.component';
import { TipoviZvanjaComponent } from './page/tipovi-zvanja/tipovi-zvanja.component';
import { UniverzitetiComponent } from './page/univerziteti/univerziteti.component';
import { ZvanjaComponent } from './page/zvanja/zvanja.component';
import { TabelaAdreseComponent } from './page/adrese/tabela-adrese/tabela-adrese.component';
import { FormaAdreseComponent } from './page/adrese/forma-adrese/forma-adrese.component';
import { DetailsAdreseComponent } from './page/adrese/details-adrese/details-adrese.component';
import { TabelaDrzaveComponent } from './page/drzave/tabela-drzave/tabela-drzave.component';
import { FormaDrzaveComponent } from './page/drzave/forma-drzave/forma-drzave.component';
import { DetailsDrzavaComponent } from './page/drzave/details-drzava/details-drzava.component';
import { TabelaFakultetaComponent } from './page/fakulteti/tabela-fakulteta/tabela-fakulteta.component';
import { FormaFakultetaComponent } from './page/fakulteti/forma-fakulteta/forma-fakulteta.component';
import { DetailsFakultetaComponent } from './page/fakulteti/details-fakulteta/details-fakulteta.component';
import { TabelaGodineStudijaComponent } from './page/godine-studija/tabela-godine-studija/tabela-godine-studija.component';
import { FormaGodineStudijaComponent } from './page/godine-studija/forma-godine-studija/forma-godine-studija.component';
import { DetailsGodineStudijaComponent } from './page/godine-studija/details-godine-studija/details-godine-studija.component';
import { TabelaIshodaComponent } from './page/ishodi/tabela-ishoda/tabela-ishoda.component';
import { FormaIshodaComponent } from './page/ishodi/forma-ishoda/forma-ishoda.component';
import { DetailsIshodaComponent } from './page/ishodi/details-ishoda/details-ishoda.component';
import { TabelaMestaComponent } from './page/mesta/tabela-mesta/tabela-mesta.component';
import { FormaMestaComponent } from './page/mesta/forma-mesta/forma-mesta.component';
import { DetailsMestaComponent } from './page/mesta/details-mesta/details-mesta.component';
import { TabelaNastavnikaComponent } from './page/nastavnici/tabela-nastavnika/tabela-nastavnika.component';
import { FormaNastavnikaComponent } from './page/nastavnici/forma-nastavnika/forma-nastavnika.component';
import { PretragaNastavnikaComponent } from './page/nastavnici/pretraga-nastavnika/pretraga-nastavnika.component';
import { DetailsNastavnikaComponent } from './page/nastavnici/details-nastavnika/details-nastavnika.component';
import { TabelaNastavnikaNaRealizacijiComponent } from './page/nastavnici-na-realizaciji/tabela-nastavnika-na-realizaciji/tabela-nastavnika-na-realizaciji.component';
import { FormaNastavnikaNaRealizacijiComponent } from './page/nastavnici-na-realizaciji/forma-nastavnika-na-realizaciji/forma-nastavnika-na-realizaciji.component';
import { DetailsNastavnikaNaRealizacijiComponent } from './page/nastavnici-na-realizaciji/details-nastavnika-na-realizaciji/details-nastavnika-na-realizaciji.component';
import { TabelaNaucneOblastiComponent } from './page/naucne-oblasti/tabela-naucne-oblasti/tabela-naucne-oblasti.component';
import { FormaNaucneOblastiComponent } from './page/naucne-oblasti/forma-naucne-oblasti/forma-naucne-oblasti.component';
import { DetailsNaucneOblastiComponent } from './page/naucne-oblasti/details-naucne-oblasti/details-naucne-oblasti.component';
import { TabelaPohadjanjaPredmetaComponent } from './page/pohadjanja-predmeta/tabela-pohadjanja-predmeta/tabela-pohadjanja-predmeta.component';
import { FormaPohadjanjaPredmetaComponent } from './page/pohadjanja-predmeta/forma-pohadjanja-predmeta/forma-pohadjanja-predmeta.component';
import { DetailsPohadjanjaPredmetaComponent } from './page/pohadjanja-predmeta/details-pohadjanja-predmeta/details-pohadjanja-predmeta.component';
import { TabelaPredmetaComponent } from './page/predmeti/tabela-predmeta/tabela-predmeta.component';
import { FormaPredmetaComponent } from './page/predmeti/forma-predmeta/forma-predmeta.component';
import { DetailsPredmetaComponent } from './page/predmeti/details-predmeta/details-predmeta.component';
import { TabelaRealizacijePredmetaComponent } from './page/realizacije-predmeta/tabela-realizacije-predmeta/tabela-realizacije-predmeta.component';
import { FormaRealizacijePredmetaComponent } from './page/realizacije-predmeta/forma-realizacije-predmeta/forma-realizacije-predmeta.component';
import { DetailsRealizacijePredmetaComponent } from './page/realizacije-predmeta/details-realizacije-predmeta/details-realizacije-predmeta.component';
import { TabelaStudentaComponent } from './page/studenti/tabela-studenta/tabela-studenta.component';
import { FormaStudentaComponent } from './page/studenti/forma-studenta/forma-studenta.component';
import { DetailsStudentaComponent } from './page/studenti/details-studenta/details-studenta.component';
import { PretragaStudentaComponent } from './page/studenti/pretraga-studenta/pretraga-studenta.component';
import { TabelaStudentaNaGodiniComponent } from './page/studenti-na-godini/tabela-studenta-na-godini/tabela-studenta-na-godini.component';
import { FormaStudentaNaGodiniComponent } from './page/studenti-na-godini/forma-studenta-na-godini/forma-studenta-na-godini.component';
import { DetailsStudentaNaGodiniComponent } from './page/studenti-na-godini/details-studenta-na-godini/details-studenta-na-godini.component';
import { TabelaStudijskihProgramaComponent } from './page/studijski-programi/tabela-studijskih-programa/tabela-studijskih-programa.component';
import { FormaStudijskihProgramaComponent } from './page/studijski-programi/forma-studijskih-programa/forma-studijskih-programa.component';
import { DetailsStudijskihProgramaComponent } from './page/studijski-programi/details-studijskih-programa/details-studijskih-programa.component';
import { TabelaTipaNastaveComponent } from './page/tipovi-nastave/tabela-tipa-nastave/tabela-tipa-nastave.component';
import { FormaTipaNastaveComponent } from './page/tipovi-nastave/forma-tipa-nastave/forma-tipa-nastave.component';
import { DetailsTipaNastaveComponent } from './page/tipovi-nastave/details-tipa-nastave/details-tipa-nastave.component';
import { TabelaTipaZvanjaComponent } from './page/tipovi-zvanja/tabela-tipa-zvanja/tabela-tipa-zvanja.component';
import { FormaTipaZvanjaComponent } from './page/tipovi-zvanja/forma-tipa-zvanja/forma-tipa-zvanja.component';
import { DetailsTipaZvanjaComponent } from './page/tipovi-zvanja/details-tipa-zvanja/details-tipa-zvanja.component';
import { TabelaUniverzitetaComponent } from './page/univerziteti/tabela-univerziteta/tabela-univerziteta.component';
import { FormaUniverzitetaComponent } from './page/univerziteti/forma-univerziteta/forma-univerziteta.component';
import { DetailsUniverzitetaComponent } from './page/univerziteti/details-univerziteta/details-univerziteta.component';
import { TabelaZvanjaComponent } from './page/zvanja/tabela-zvanja/tabela-zvanja.component';
import { FormaZvanjaComponent } from './page/zvanja/forma-zvanja/forma-zvanja.component';
import { DetailsZvanjaComponent } from './page/zvanja/details-zvanja/details-zvanja.component';
import { TreeViewComponent } from './tree-view/tree-view.component';
import { PageTreeViewComponent } from './tree-view/page-tree-view/page-tree-view.component';

@NgModule({
  declarations: [
    AppComponent,
    MeniComponent,
    WelcomeComponent,
    NotFoundComponent,
    PomeranjeKursoraDirective,
    PorukaOGresciDirective,
    ProveraNepostojecihVrednostiDirective,
    LoginComponent,
    AdreseComponent,
    DrzaveComponent,
    FakultetiComponent,
    GodineStudijaComponent,
    IshodiComponent,
    MestaComponent,
    NastavniciComponent,
    NastavniciNaRealizacijiComponent,
    NaucneOblastiComponent,
    PohadjanjaPredmetaComponent,
    PredmetiComponent,
    RealizacijePredmetaComponent,
    StudentiComponent,
    StudentiNaGodiniComponent,
    StudijskiProgramiComponent,
    TipoviNastaveComponent,
    TipoviZvanjaComponent,
    UniverzitetiComponent,
    ZvanjaComponent,
    TabelaAdreseComponent,
    FormaAdreseComponent,
    DetailsAdreseComponent,
    TabelaDrzaveComponent,
    FormaDrzaveComponent,
    DetailsDrzavaComponent,
    TabelaFakultetaComponent,
    FormaFakultetaComponent,
    DetailsFakultetaComponent,
    TabelaGodineStudijaComponent,
    FormaGodineStudijaComponent,
    DetailsGodineStudijaComponent,
    TabelaIshodaComponent,
    FormaIshodaComponent,
    DetailsIshodaComponent,
    TabelaMestaComponent,
    FormaMestaComponent,
    DetailsMestaComponent,
    TabelaNastavnikaComponent,
    FormaNastavnikaComponent,
    PretragaNastavnikaComponent,
    DetailsNastavnikaComponent,
    TabelaNastavnikaNaRealizacijiComponent,
    FormaNastavnikaNaRealizacijiComponent,
    DetailsNastavnikaNaRealizacijiComponent,
    TabelaNaucneOblastiComponent,
    FormaNaucneOblastiComponent,
    DetailsNaucneOblastiComponent,
    TabelaPohadjanjaPredmetaComponent,
    FormaPohadjanjaPredmetaComponent,
    DetailsPohadjanjaPredmetaComponent,
    TabelaPredmetaComponent,
    FormaPredmetaComponent,
    DetailsPredmetaComponent,
    TabelaRealizacijePredmetaComponent,
    FormaRealizacijePredmetaComponent,
    DetailsRealizacijePredmetaComponent,
    TabelaStudentaComponent,
    FormaStudentaComponent,
    DetailsStudentaComponent,
    PretragaStudentaComponent,
    TabelaStudentaNaGodiniComponent,
    FormaStudentaNaGodiniComponent,
    DetailsStudentaNaGodiniComponent,
    TabelaStudijskihProgramaComponent,
    FormaStudijskihProgramaComponent,
    DetailsStudijskihProgramaComponent,
    TabelaTipaNastaveComponent,
    FormaTipaNastaveComponent,
    DetailsTipaNastaveComponent,
    TabelaTipaZvanjaComponent,
    FormaTipaZvanjaComponent,
    DetailsTipaZvanjaComponent,
    TabelaUniverzitetaComponent,
    FormaUniverzitetaComponent,
    DetailsUniverzitetaComponent,
    TabelaZvanjaComponent,
    FormaZvanjaComponent,
    DetailsZvanjaComponent,
    TreeViewComponent,
    PageTreeViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true}],//Login
  bootstrap: [AppComponent]
})
export class AppModule { }
