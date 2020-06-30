import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceplanComponent } from './serviceplan.component';

describe('ServiceplanComponent', () => {
  let component: ServiceplanComponent;
  let fixture: ComponentFixture<ServiceplanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceplanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
