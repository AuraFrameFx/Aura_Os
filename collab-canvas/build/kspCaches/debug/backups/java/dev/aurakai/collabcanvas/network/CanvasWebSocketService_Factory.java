package dev.aurakai.collabcanvas.network;

import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class CanvasWebSocketService_Factory implements Factory<CanvasWebSocketService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Gson> gsonProvider;

  private CanvasWebSocketService_Factory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Gson> gsonProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public CanvasWebSocketService get() {
    return newInstance(okHttpClientProvider.get(), gsonProvider.get());
  }

  public static CanvasWebSocketService_Factory create(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Gson> gsonProvider) {
    return new CanvasWebSocketService_Factory(okHttpClientProvider, gsonProvider);
  }

  public static CanvasWebSocketService newInstance(OkHttpClient okHttpClient, Gson gson) {
    return new CanvasWebSocketService(okHttpClient, gson);
  }
}
