import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AlertifyService {

  constructor(private toastr: ToastrService) { }

  confirm(message: string, okCallBack: () => any) {
    // alertify.confirm(message, (e: any) => {
    //   if (e) {
    //     okCallBack();
    //   }
    //   else {

    //   }
    // });
  }

  success(message: string) {
    // alertify.set('notifier', 'position', 'top-center');
    // alertify.success(message);
  //   iziToast.success({
  //     title: 'OK',
  //     message: 'Successfully inserted record!',
  // });
  this.toastr.success(message, "Success");
  }

  error(message: string) {
    // alertify.set('notifier', 'position', 'bottom-center');
    // alertify.error(message);
  }

  warning(message: string) {
    // alertify.warning(message);
  }

  message(message: string) {
    // alertify.message(message);
  }
}
