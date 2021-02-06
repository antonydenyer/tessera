package com.quorum.tessera.privacygroup.publisher;

import com.quorum.tessera.privacygroup.publish.PrivacyGroupPublisher;
import org.junit.Test;

import java.util.Optional;
import java.util.ServiceLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PrivacyGroupPublisherTest {

    @Test
    public void create() {
        PrivacyGroupPublisher privacyGroupPublisher = mock(PrivacyGroupPublisher.class);
        ServiceLoader<PrivacyGroupPublisher> serviceLoader = mock(ServiceLoader.class);
        when(serviceLoader.findFirst()).thenReturn(Optional.of(privacyGroupPublisher));
        PrivacyGroupPublisher result;
        try(var serviceLoaderMockedStatic = mockStatic(ServiceLoader.class)) {

            serviceLoaderMockedStatic.when(() -> ServiceLoader.load(PrivacyGroupPublisher.class))
                .thenReturn(serviceLoader);

            result = PrivacyGroupPublisher.create();

            serviceLoaderMockedStatic.verify(() -> ServiceLoader.load(PrivacyGroupPublisher.class));
            serviceLoaderMockedStatic.verifyNoMoreInteractions();

        }
        verify(serviceLoader).findFirst();
        verifyNoMoreInteractions(serviceLoader);
        assertThat(result).isNotNull().isSameAs(privacyGroupPublisher);
    }
}
