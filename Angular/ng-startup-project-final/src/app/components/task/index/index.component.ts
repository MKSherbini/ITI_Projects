import { Component, OnInit } from '@angular/core';
import { Task } from '../task.model';
import { TaskService } from '../task.service';
import { TaskCreateViewModel } from './../task-create.model';
import { UserService } from './../../user/user.service';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  name:string="Mohamed Ali";

  tasks:Task[]=[];
  constructor(private _taskService:TaskService
    ,private _userService:UserService
    ,private _router:Router
    ) { }

  ngOnInit(): void {
    // if(!this._userService.isLogged())
    // {
    //   this._router.navigateByUrl('/user/login');
    // }
// forkJoin([this._taskService.get(),this._taskService.get()]).subscribe(response=>{
//   response[0].Data;
//   response[1].Data
// })


    this._taskService.get().subscribe(
      response=>{
        this.tasks=response.Data;
      },
      error=>{
        //if false
        alert("Error");
      }
      );
    //alert("Finished");
    
  }
  showTasksCount(){
   this._taskService.get().subscribe(
      response=>{
        alert(response.Data.length);
      },
      error=>{
        //if false
        alert("Error");
      }
      );
  }
addTask(title:string){

let newTask=new TaskCreateViewModel();
newTask.Title=title;

this._taskService.post(newTask).subscribe(
  response=>{
    let task=new Task();
    task.Title=title;
    this.tasks.unshift(task);
    //alert(response['Message']);
  },
  error=>{}
);

}

delete(index:number)
{
  let task=this.tasks[index];
  this._taskService.delete(task.ID).subscribe(
response=>{
  alert(response.Message);
  this.tasks.splice(index,1);
},
error=>{}


  );
}

deleteFromService(index:number)
{
  let task=this.tasks[index];
  this._taskService.delete(task.ID).subscribe(
response=>{
  alert(response.Message);
  this.tasks.splice(index,1);
},
error=>{}


  );
}
  getPendingTasks():number{
    return this.tasks.filter(x=>!x.IsDone).length;
  }

}
