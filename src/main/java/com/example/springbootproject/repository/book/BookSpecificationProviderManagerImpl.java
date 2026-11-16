package com.example.springbootproject.repository.book;

import com.example.springbootproject.exception.EntityNotFoundException;
import com.example.springbootproject.repository.BookSpecificationProvider;
import com.example.springbootproject.repository.BookSpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManagerImpl implements BookSpecificationProviderManager {
    private final List<BookSpecificationProvider> bookSpecificationProviders;

    @Override
    public BookSpecificationProvider getBookSpecificationProvider(String key) {
        return bookSpecificationProviders.stream().filter(sp -> sp.getKey()
                .equals(key))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Can not find specification "
                        + "provider with the key:" + key));
    }
}
