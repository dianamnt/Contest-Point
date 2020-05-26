import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SuccessComponent } from './success/success.component';
import { 
  AuthGuardService as AuthGuard 
} from './_guards/auth-guard.service';
import { AddContestComponent} from './add-contest/add-contest.component';


const routes: Routes = [
  {path: 'welcome', component: WelcomeComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  {path: 'success', component: SuccessComponent, canActivate: [AuthGuard]},
  {path: 'add', component: AddContestComponent, canActivate: [AuthGuard]},
  { path: '**', redirectTo: 'welcome' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
