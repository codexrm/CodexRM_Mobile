package io.github.codexrm_mobile.model.utils;

import io.github.codexrm_mobile.model.dto.ArticleReferenceDTO;
import io.github.codexrm_mobile.model.dto.BookLetReferenceDTO;
import io.github.codexrm_mobile.model.dto.BookReferenceDTO;
import io.github.codexrm_mobile.model.dto.BookSectionReferenceDTO;
import io.github.codexrm_mobile.model.dto.ConferencePaperReferenceDTO;
import io.github.codexrm_mobile.model.dto.ConferenceProceedingsReferenceDTO;
import io.github.codexrm_mobile.model.dto.ReferenceDTO;
import io.github.codexrm_mobile.model.dto.ThesisReferenceDTO;
import io.github.codexrm_mobile.model.dto.WebPageReferenceDTO;
import io.github.codexrm_mobile.model.models.ArticleReference;
import io.github.codexrm_mobile.model.models.BookLetReference;
import io.github.codexrm_mobile.model.models.BookReference;
import io.github.codexrm_mobile.model.models.BookSectionReference;
import io.github.codexrm_mobile.model.models.ConferencePaperReference;
import io.github.codexrm_mobile.model.models.ConferenceProceedingReference;
import io.github.codexrm_mobile.model.models.Reference;
import io.github.codexrm_mobile.model.models.ThesisReference;
import io.github.codexrm_mobile.model.models.WebPageReference;

public class DTOConvert {

    private final ValidateReference validation;

    public DTOConvert() {
        this.validation = new ValidateReference();
    }

    public ReferenceDTO createReferenceDTO(Reference reference){

        if(reference instanceof ArticleReference){
            ArticleReference article = (ArticleReference) reference;
            return new ArticleReferenceDTO(article.getTitle(), article.getYear(), article.getMonth(), article.getNote(), article.getId(),article.getAuthor(), article.getJournal(), article.getVolume(), article.getNumber(),
                    article.getPages(), article.getIssn());

        }else if(reference instanceof BookSectionReference){
            BookSectionReference section = (BookSectionReference) reference;
            return new BookSectionReferenceDTO(section.getTitle(), section.getYear(), section.getMonth(), section.getNote(), section.getId(), section.getAuthor(), section.getEditor(), section.getPublisher(), section.getVolume(),
                    section.getSeries(), section.getNumber(), section.getAddress(), section.getEdition(), section.getIsbn(), section.getChapter(), section.getPages(), section.getType());

        } else if(reference instanceof BookReference){
            BookReference book = (BookReference) reference;
            return new BookReferenceDTO(book.getTitle(), book.getYear(), book.getMonth(), book.getNote(), book.getId(), book.getAuthor(), book.getEditor(), book.getPublisher(), book.getVolume(), book.getSeries(), book.getNumber(),
                    book.getAddress(), book.getEdition(), book.getIsbn());

        } else if(reference instanceof BookLetReference){
            BookLetReference let = (BookLetReference) reference;
            return new BookLetReferenceDTO(let.getTitle(), let.getYear(), let.getMonth(), let.getNote(), let.getId(), let.getAuthor(), let.getHowpublished(), let.getAddress());

        } else if(reference instanceof ThesisReference){
            ThesisReference thesis = (ThesisReference) reference;
            return new ThesisReferenceDTO(thesis.getTitle(), thesis.getYear(), thesis.getMonth(), thesis.getNote(), thesis.getId(), thesis.getAuthor(), thesis.getSchool(), thesis.getType(), thesis.getAddress());

        } else if(reference instanceof ConferencePaperReference){
            ConferencePaperReference paper = (ConferencePaperReference) reference;
            return new ConferencePaperReferenceDTO(paper.getTitle(), paper.getYear(), paper.getMonth(), paper.getNote(), paper.getId(), paper.getAuthor(), paper.getBookTitle(), paper.getEditor(), paper.getVolume(), paper.getNumber(),
                    paper.getSeries(), paper.getPages(), paper.getAddress(), paper.getOrganization(), paper.getPublisher());

        } else if(reference instanceof ConferenceProceedingReference){
            ConferenceProceedingReference proceedings = (ConferenceProceedingReference) reference;
            return new ConferenceProceedingsReferenceDTO(proceedings.getTitle(), proceedings.getYear(), proceedings.getMonth(), proceedings.getNote(), proceedings.getId(), proceedings.getEditor(), proceedings.getVolume(),
                    proceedings.getNumber(), proceedings.getSeries(), proceedings.getAddress(), proceedings.getPublisher(), proceedings.getOrganization(), proceedings.getIsbn());

        } else{
            WebPageReference webPage = (WebPageReference) reference;
            return new WebPageReferenceDTO(webPage.getTitle(), webPage.getYear(), webPage.getMonth(), webPage.getNote(), webPage.getId(), webPage.getAuthor(), webPage.getUrl());
        }
    }

    public Reference createReference(ReferenceDTO referenceDTO){

        if(referenceDTO instanceof ArticleReferenceDTO){
            ArticleReferenceDTO ArticleReferenceDTO = (ArticleReferenceDTO) referenceDTO;
            ArticleReference  article = new ArticleReference(ArticleReferenceDTO.getId(), ArticleReferenceDTO.getTitle(), ArticleReferenceDTO.getYear(), ArticleReferenceDTO.getMonth(), ArticleReferenceDTO.getNote(),
                    ArticleReferenceDTO.getAuthor(), ArticleReferenceDTO.getJournal(), ArticleReferenceDTO.getVolume(), ArticleReferenceDTO.getNumber(), ArticleReferenceDTO.getPages(), ArticleReferenceDTO.getIssn(), false);
            return validation.validateRequiredArticle(article);

        }else if(referenceDTO instanceof BookSectionReferenceDTO){
            BookSectionReferenceDTO sectionDTO = (BookSectionReferenceDTO) referenceDTO;
            BookSectionReference section = new BookSectionReference(sectionDTO.getId(), sectionDTO.getTitle(), sectionDTO.getYear(), sectionDTO.getMonth(), sectionDTO.getNote(), sectionDTO.getAuthor(), sectionDTO.getEditor(),
                    sectionDTO.getPublisher(), sectionDTO.getVolume(), sectionDTO.getNumber(), sectionDTO.getSeries(), sectionDTO.getAddress(), sectionDTO.getEdition(), sectionDTO.getIsbn(), sectionDTO.getChapter(),
                    sectionDTO.getPages(), sectionDTO.getType(), false);
            return validation.validateRequiredBookSection(section);

        } else if(referenceDTO instanceof BookReferenceDTO){
            BookReferenceDTO bookDTO = (BookReferenceDTO) referenceDTO;
            BookReference book = new BookReference(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getYear(), bookDTO.getMonth(), bookDTO.getNote(), bookDTO.getAuthor(), bookDTO.getEditor(), bookDTO.getPublisher(), bookDTO.getVolume(),
                    bookDTO.getNumber(), bookDTO.getSeries(), bookDTO.getAddress(), bookDTO.getEdition(), bookDTO.getIsbn(), false);
            return validation.validateRequiredBook(book);

        } else if(referenceDTO instanceof BookLetReferenceDTO){
            BookLetReferenceDTO letDTO = (BookLetReferenceDTO) referenceDTO;
            BookLetReference let = new BookLetReference(letDTO.getId(), letDTO.getTitle(), letDTO.getYear(), letDTO.getMonth(), letDTO.getNote(), letDTO.getAuthor(), letDTO.getHowpublished(), letDTO.getAddress(), false);
            return validation.validateRequiredBookLet(let);

        } else if(referenceDTO instanceof ThesisReferenceDTO){
            ThesisReferenceDTO thesisDTO = (ThesisReferenceDTO) referenceDTO;
            ThesisReference thesis = new ThesisReference(thesisDTO.getId(), thesisDTO.getTitle(), thesisDTO.getYear(), thesisDTO.getMonth(), thesisDTO.getNote(), thesisDTO.getAuthor(), thesisDTO.getSchool(), thesisDTO.getType(),
                    thesisDTO.getAddress(), false);
            return validation.validateRequiredThesis(thesis);

        } else if(referenceDTO instanceof ConferencePaperReferenceDTO){
            ConferencePaperReferenceDTO paperDTO = (ConferencePaperReferenceDTO) referenceDTO;
            ConferencePaperReference paper =  new ConferencePaperReference(paperDTO.getId(), paperDTO.getTitle(), paperDTO.getYear(), paperDTO.getMonth(), paperDTO.getNote(), paperDTO.getAuthor(), paperDTO.getBookTitle(),
                    paperDTO.getEditor(), paperDTO.getNumber(), paperDTO.getSeries(), paperDTO.getPublisher(), paperDTO.getVolume(), paperDTO.getAddress(), paperDTO.getPages(), paperDTO.getOrganization(), false);
            return validation.validateRequiredConferencePaper(paper);

        } else if(referenceDTO instanceof ConferenceProceedingsReferenceDTO){
            ConferenceProceedingsReferenceDTO proceedingsDTO = (ConferenceProceedingsReferenceDTO) referenceDTO;
            ConferenceProceedingReference proceedings = new ConferenceProceedingReference(proceedingsDTO.getId(), proceedingsDTO.getTitle(), proceedingsDTO.getYear(), proceedingsDTO.getMonth(), proceedingsDTO.getNote(),
                    proceedingsDTO.getEditor(), proceedingsDTO.getVolume(), proceedingsDTO.getNumber(), proceedingsDTO.getSeries(), proceedingsDTO.getAddress(), proceedingsDTO.getPublisher(), proceedingsDTO.getIsbn(),
                    proceedingsDTO.getOrganization(), false);
            return validation.validateRequiredConferenceProceedings(proceedings);

        } else if(referenceDTO instanceof WebPageReferenceDTO){
            WebPageReferenceDTO webPage = (WebPageReferenceDTO) referenceDTO;
            return new WebPageReference(webPage.getId(), webPage.getTitle(), webPage.getYear(), webPage.getMonth(), webPage.getNote(), webPage.getAuthor(), webPage.getUrl(), false);
        }
        return null;
    }
}

