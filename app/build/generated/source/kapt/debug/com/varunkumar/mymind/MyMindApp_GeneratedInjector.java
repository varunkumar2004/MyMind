package com.varunkumar.mymind;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = MyMindApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface MyMindApp_GeneratedInjector {
  void injectMyMindApp(MyMindApp myMindApp);
}
