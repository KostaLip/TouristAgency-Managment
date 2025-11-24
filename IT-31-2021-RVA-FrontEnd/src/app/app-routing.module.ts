import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AranzmanComponent } from './components/main/aranzman/aranzman.component';
import { DestinacijaComponent } from './components/main/destinacija/destinacija.component';
import { HotelComponent } from './components/main/hotel/hotel.component';
import { TuristickaAgencijaComponent } from './components/main/turisticka-agencija/turisticka-agencija.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { HomePageComponent } from './components/utility/home-page/home-page.component';

const routes: Routes = [
  {path: 'turisticka_agencija', component:TuristickaAgencijaComponent},
  {path: 'destinacija', component:DestinacijaComponent},
  {path: 'aranzman', component:AranzmanComponent},
  {path: 'hotel', component:HotelComponent},
  {path: 'about', component:AboutComponent},
  {path: 'author', component:AuthorComponent},
  {path: 'home_page', component:HomePageComponent},
  {path: '', component:HomePageComponent, pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
