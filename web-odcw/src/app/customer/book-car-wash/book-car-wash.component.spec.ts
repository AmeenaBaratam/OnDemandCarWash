import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCarWashComponent } from './book-car-wash.component';

describe('BookCarWashComponent', () => {
  let component: BookCarWashComponent;
  let fixture: ComponentFixture<BookCarWashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookCarWashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookCarWashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
