package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.config.IOService;
import ru.otus.project.domain.History;
import ru.otus.project.rest.FeignForHistory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final FeignForHistory feignHistory;
    private final IOService ioService;

    @Override
    public List<History> getVineHistoryById() {
        ioService.output("Введите id вина");
        long id = Long.parseLong(ioService.input());
        List<History> list = feignHistory.getVineHistoryById(id);
        if (list.size() == 0) ioService.output("История цен отсутствует");
        list.forEach(i -> ioService.output(i.toString()));
        return list;
    }

    @Override
    public List<History> getVineHistoryByName() {
        ioService.output("Введите наименование вина");
        String name = ioService.input();
        List<History> list = feignHistory.getVineHistoryByName(name);
        if (list.size() == 0) ioService.output("История цен отсутствует");
        list.forEach(i -> ioService.output(i.toString()));
        return list;
    }

    @Override
    public void deleteVineHistoryById() {
        ioService.output("Введите id вина, историю цен которого выхотите удалить");
        long id = Long.parseLong(ioService.input());
        feignHistory.deleteVineById(id);
        ioService.output("История цен удалена для id " + id);
    }

    @Override
    public void deleteAllHistory() {
        feignHistory.deleteAllHistory();
        ioService.output("Вся история цен удалена");
    }


}
