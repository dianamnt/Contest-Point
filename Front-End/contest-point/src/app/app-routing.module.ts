import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SuccessComponent } from './success/success.component';
import { 
  AuthGuardService as AuthGuard 
} from './_guards/auth-guard.service';
import { AddContestComponent} from './add-contest/add-contest.component';
import { ContestComponent } from './contest/contest.component';
import { EventsComponent } from './events/events.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { TagComponent } from './tag/tag.component';


const routes: Routes = [
  {path: 'welcome', component: WelcomeComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  {path: 'success', component: SuccessComponent, canActivate: [AuthGuard]},
  {path: 'add', component: AddContestComponent, canActivate: [AuthGuard]},
  {path: 'contest', component: ContestComponent, canActivate: [AuthGuard]},
  {path: 'my-contests', component: EventsComponent, canActivate: [AuthGuard]},
  {path: 'favorites', component: FavoritesComponent, canActivate: [AuthGuard]},
  {path: 'tag', component: TagComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: 'welcome'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
