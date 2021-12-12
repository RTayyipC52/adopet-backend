package adopet.project.business.concretes;

import adopet.project.business.abstracts.AnimalTypeService;
import adopet.project.core.utilities.results.DataResult;
import adopet.project.core.utilities.results.ErrorDataResult;
import adopet.project.core.utilities.results.SuccessDataResult;
import adopet.project.dataAccess.abstracts.AnimalTypeDao;
import adopet.project.entities.concretes.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalTypeManager implements AnimalTypeService {

    private AnimalTypeDao animalTypeDao;

    @Autowired
    public AnimalTypeManager(AnimalTypeDao animalTypeDao) {
        this.animalTypeDao = animalTypeDao;
    }

    @Override
    public DataResult<List<AnimalType>> getAll() {
        return new SuccessDataResult<List<AnimalType>>
                (this.animalTypeDao.findAll(),"Hayvan türleri listelendi");
    }

    @Override
    public DataResult<List<AnimalType>> getByTypeId(int typeId) {
        if (this.animalTypeDao.getByTypeId(typeId).isEmpty()) {
            return new ErrorDataResult<List<AnimalType>>("Girdiğiniz sayıda hayvan türü yoktur");
        }else {
            return new SuccessDataResult<List<AnimalType>>
                    (this.animalTypeDao.getByTypeId(typeId), "Id'sine göre tür listelendi");
        }
    }
}